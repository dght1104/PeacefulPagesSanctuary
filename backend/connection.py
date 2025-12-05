import pyodbc

server = 'DGHT1104'
database = 'PeacefulPagesSanctuary'
driver = 'ODBC Driver 17 for SQL Server'

# Chuẩn Trusted Connection (Windows auth)
conn_str = (
    f'DRIVER={{ODBC Driver 17 for SQL Server}};'
    f'SERVER={server};'
    f'DATABASE={database};'
    'Trusted_Connection=yes;'
)

try:
    conn = pyodbc.connect(conn_str)
    cursor = conn.cursor()
    cursor.execute("SELECT 1")
    print("✅ Connection successful:", cursor.fetchone())
    conn.close()
except Exception as e:
    print("❌ Connection failed:", e)
