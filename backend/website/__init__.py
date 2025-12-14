from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flask_mail import Mail
from flask_cors import CORS

# ===== Extensions (global) =====
db = SQLAlchemy()
migrate = Migrate()
mail = Mail()

# ===== DB config =====
server = 'DGHT1104'
database = 'PeacefulPagesSanctuary'
driver = 'ODBC Driver 17 for SQL Server'


def create_app():
    app = Flask(__name__)

    # ===== App config =====
    app.config.update(
        SECRET_KEY='giahan',

        SQLALCHEMY_DATABASE_URI=(
            f"mssql+pyodbc://{server}/{database}"
            f"?driver={driver.replace(' ', '+')}&Trusted_Connection=yes"
        ),
        SQLALCHEMY_TRACK_MODIFICATIONS=False,

        MAIL_SERVER='smtp.gmail.com',
        MAIL_PORT=587,
        MAIL_USE_TLS=True,
        MAIL_USERNAME='trandonggiahan2003@gmail.com',
        MAIL_PASSWORD='yjqulsrnrzgwedaq',
        MAIL_DEFAULT_SENDER='trandonggiahan2003@gmail.com'
    )

    # ===== Init extensions =====
    CORS(app, supports_credentials=True)
    db.init_app(app)
    migrate.init_app(app, db)
    mail.init_app(app)

    # ===== Register API blueprints =====
    from .api.auth_api import api_auth
    app.register_blueprint(api_auth, url_prefix='/api')

    # 👉 chỉ mở khi file tồn tại
    try:
        from .api.product_api import api_product
        app.register_blueprint(api_product, url_prefix='/api')
    except ImportError:
        pass

    return app
