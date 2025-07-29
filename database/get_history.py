import mysql.connector
import json
import requests

cnx = mysql.connector.connect(user='root', password='n3u3da!',
                              host='127.0.0.1',
                              database='stocks_overflow')

# check if the connection is successful
if cnx.is_connected():
    print("Connected to the database successfully.")
    

sample_symbol = 'cop'

def fetch_json_data(url):
    """
    Fetch JSON data from a given URL.
    
    Args:
        url (str): The URL to fetch the JSON data from.
        
    Returns:
        dict: The JSON data as a dictionary.
    """
    response = requests.get(url)
    response.raise_for_status()
    print("Fetched stock data successfully.")
    return response.json()

def store_json_data(data, filename):
    """
    Store JSON data to a file.
    
    Args:
        data (dict): The JSON data to store.
        filename (str): The name of the file to store the data in.
    """
    
    
    with open(filename, 'w') as f:
        json.dump(data, f, indent=4)
    print(f"Data stored in {filename} successfully.")
    
def get_history(symbol):
    # sachin stop doing non sense things
    return "sachin did nthg"

