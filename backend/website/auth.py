from flask import Blueprint, render_template, request, flash, redirect, url_for
from .models import Customer
from . import db
from flask_login import login_user, logout_user, login_required, current_user
import hashlib
from werkzeug.security import generate_password_hash
auth = Blueprint('auth', __name__)
from sqlalchemy import text
@auth.route('/signup', methods=["GET", "POST"])
def signup():
    if request.method == "POST":
        user_name = request.form.get('user_name')
        email = request.form.get('email')
        password = request.form.get('password')
        password1 = request.form.get('password1')

        # Kiểm tra user/email tồn tại
        if Customer.query.filter_by(cus_username=user_name).first():
            flash("Tên đăng nhập đã tồn tại", "error")
        elif Customer.query.filter_by(cus_email=email).first():
            flash("Email đã tồn tại", "error")
        elif len(user_name) < 6:
            flash("Tên đăng nhập phải dài hơn 6 ký tự", "error")
        elif len(password) < 6:
            flash("Mật khẩu phải dài hơn 6 ký tự", "error")
        elif password != password1:
            flash("Mật khẩu không trùng khớp", "error")
        else:
            try:
                print(f"Tên user: {user_name}")
                # Hash password trước khi lưu
                # hashed_password = generate_password_hash(password)

                # Raw SQL insert để tránh lỗi trigger + OUTPUT
                sql = sql = text("""
                            INSERT INTO Customer
                            (cus_name, cus_username, cus_email, cus_password, is_verified, is_active, cus_group, total_spent)
                            VALUES (:name, :username, :email, :password, 1, 1, 'Silver', 0)
                        """)
                db.session.execute(sql, {
                    "name": user_name,
                    "username": user_name,
                    "email": email,
                    "password": password
                })
                db.session.commit()
                
                # Query lại user vừa insert bằng raw SQL để login
                result = db.session.execute(
                    text("SELECT * FROM Customer WHERE cus_username = :username"),
                    {"username": user_name}
                ).fetchone()

                if result:
                    new_user = Customer(
                        cus_id=result.cus_id,
                        cus_name=result.cus_name,
                        cus_username=result.cus_username,
                        cus_email=result.cus_email,
                        cus_password=result.cus_password,
                        is_verified=result.is_verified
                    )
                    login_user(new_user, remember=True)
                    print("Thành công")
                    flash("Đăng ký & đăng nhập thành công!", "success")
                    return redirect(url_for('views.home'))
                else:
                    flash("Không thể tạo tài khoản, thử lại sau.", "error")

            except Exception as e:
                db.session.rollback()
                flash(f"Lỗi database: {e}", "error")
                print(">>> DB ERROR:", e)

    return render_template('auth/signup.html', user=current_user)



@auth.route('/login', methods=["GET", "POST"])
def login():
    if request.method == "POST":
        email = request.form.get('email')
        password = request.form.get('password')

        user = Customer.query.filter_by(cus_email=email).first()
        if not user:
            flash("Email không tồn tại", category='error')
        else:
            # hashed_password = hashlib.sha256(password.encode()).hexdigest()
            if user.cus_password.strip() == password:
                login_user(user, remember=True)
                flash("Đăng nhập thành công!", "success")
                return redirect(url_for('views.home'))
            else:
                flash("Sai mật khẩu", category='error')

    return render_template('auth/login.html')

@auth.route('/logout')
@login_required
def logout():
    logout_user()
    flash("Đã đăng xuất", "success")
    return redirect(url_for('views.home'))

