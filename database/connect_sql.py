import mysql.connector
import json

cnx = mysql.connector.connect(user='root', password='n3u3da!',
                              host='127.0.0.1',
                              database='stocks_overflow')

# check if the connection is successful
if cnx.is_connected():
    print("Connected to the database successfully.")
    
# open the json file symovls.json
with open('database/symbols.json', 'r') as f:
    symbols_data = json.load(f)

# insert the data into the database, in the table stocks
cursor = cnx.cursor()

print(type(symbols_data), len(symbols_data))
print(type(symbols_data[0]))
print(type(symbols_data[0]['symbolID']))
print(type(symbols_data[0]['symbol']))
print(type(symbols_data[0]['companyName']))

for symbol in symbols_data:
    companyName = symbol['companyName']
    # print(type(companyName))
    symbol_name = symbol['symbol']
    # print(type(symbol))
    symbolID = symbol['symbolID']
    # print(type(symbolID))
#     print(type(symbolID), type(symbol), type(companyName))
    
    # Prepare the SQL insert statement
    insert_query = """INSERT INTO stocks (symbol_id, symbol, company_name)
VALUES (%s, %s, %s)"""
    cursor.execute(insert_query, (symbolID, symbol_name, companyName))
    cnx.commit()

print("Data inserted into the database successfully.")


cnx.close()