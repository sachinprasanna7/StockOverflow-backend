import json
import requests
import os

# store it in the following folder
folder_path = 'data/recent_data/'

base_api_uri = 'https://marketdata.neueda.com/API/StockFeed/GetStockPricesForSymbol/[Ticker]?HowManyValues=[N]'
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

    dir_path = os.path.dirname(filename)
    
    if not os.path.exists(dir_path):
        os.makedirs(dir_path)
    
    with open(filename, 'a') as f:  
        json.dump(data, f, indent=4)


def get_recent_data(symbol):
    file_name = f"{folder_path}{symbol}.json"
    data = fetch_json_data(base_api_uri.replace('[Ticker]', symbol).replace('[N]', '4000'))
    store_json_data(data, file_name)
    print(f"Recent data stored in {file_name} successfully.")
    return data

# Fetch and store recent data for each symbol
data = get_recent_data(sample_symbol)
print("Recent data fetched and stored successfully.")