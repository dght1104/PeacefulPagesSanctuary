from flask import Blueprint, jsonify
from website.models import Products

api_product = Blueprint('api_product', __name__)

@api_product.route('/products', methods=['GET'])
def get_products():
    products = Products.query.limit(20).all()

    return jsonify([
        {
            "id": p.prod_id,
            "name": p.prod_name,
            "price": float(p.prod_price or 0),
            "discount": float(p.prod_discount or 0),
            "stock": p.prod_stock,
            "description": p.prod_description
        }
        for p in products
    ])
