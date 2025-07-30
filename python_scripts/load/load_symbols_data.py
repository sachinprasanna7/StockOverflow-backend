import mysql.connector
import json

cnx = mysql.connector.connect(user='root', password='n3u3da!',
                              host='127.0.0.1',
                              database='stocks_overflow')

# check if the connection is successful
if cnx.is_connected():
    print("Connected to the database successfully.")
    
with open('data/symbols.json', 'r') as f:
    symbols_data = json.load(f)

cursor = cnx.cursor()

for symbol in symbols_data:
    companyName = symbol['companyName']
    symbol_name = symbol['symbol']
    symbolID = symbol['symbolID']
    
    insert_query = """INSERT INTO stocks (symbol_id, symbol, company_name)
VALUES (%s, %s, %s)"""
    cursor.execute(insert_query, (symbolID, symbol_name, companyName))
    cnx.commit()

print("Data inserted into the database successfully.")

cnx.close()