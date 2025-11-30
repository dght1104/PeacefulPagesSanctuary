# test_db.py
from website import create_app, db
from website.models import Customer

# Khởi tạo app
app = create_app()

with app.app_context():
    # 1️⃣ Tạo bảng nếu chưa có
    db.create_all()
    print("✅ Bảng Customer (và các bảng khác) đã tạo hoặc tồn tại")

    # 2️⃣ Insert thử 1 user
    try:
        test_user = Customer(
            cus_name="Test User",
            cus_username="testuser123",
            cus_email="testuser@example.com",
            cus_password="123456",  # Hoặc hash nếu muốn
            is_verified=True
        )
        db.session.add(test_user)
        db.session.commit()
        print(f"✅ Insert thành công! User ID: {test_user.cus_id}")
    except Exception as e:
        db.session.rollback()
        print("❌ Lỗi khi insert user:", e)
