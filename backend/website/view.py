from flask import Blueprint, render_template, request  
from flask_login import login_required, current_user
from .models import Products
views = Blueprint('views', __name__)

@views.route('/')
def home():
    return render_template('user/index.html',user=current_user)

@views.route('/product')
def product():
    page = request.args.get('page', 1, type=int)
    per_page = 20
    # Thêm order_by để MSSQL không lỗi
    pagination = Products.query.order_by(Products.prod_id).paginate(page=page, per_page=per_page)
    items = pagination.items
    return render_template('user/product.html', items=items, pagination=pagination, user=current_user)
@views.route('/about')
def about():
    return render_template('user/about.html')

@views.route('/testimonial')
def testimonial():
    return render_template('user/testimonial.html')

@views.route('/blog_list')
def blog_list():
    return render_template('user/blog_list.html')

@views.route('/contact')
def contact():
    return render_template('user/contact.html')

@views.route('/profile')
def profile():
    return render_template('user/profile.html')

@views.route('/api/hello')
def hello():
    return {"msg": "Hello Flask"}