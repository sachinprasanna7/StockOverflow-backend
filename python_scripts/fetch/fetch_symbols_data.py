import requests
import json

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


json_stock_data = fetch_json_data("https://marketdata.neueda.com/API/StockFeed/GetSymbolList")

store_json_data(json_stock_data, "data/symbols.json")




