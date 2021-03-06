# libs
import requests
import time
import csv

# my files
import addresses
requests.adapters.DEFAULT_RETRIES = 5

def job(uuid):
    first_job_result = first_job(uuid)
    result = first_job_result.text
    with open('logs/'+str(uuid) +'.csv', 'a') as writeFile:  
        writer = csv.writer(writeFile)
        percentage_done = "0"
        print(first_job_result)
        while percentage_done != "100.0":
            try:
                start_time = time.time()
                second_job_result = second_job(result)
                percentage_done = second_job_result.text
                end_time = time.time()
                writer.writerow([uuid,start_time,end_time,'success'])                
            except requests.exceptions.ConnectionError:
                end_time = time.time()
                writer.writerow([uuid,start_time,end_time,'error'])
    writeFile.close()

def first_job(argument):
    response = requests.get(addresses.synchronous(argument))
    return response

def second_job(argument):
    response = requests.get(addresses.asynchronous(argument))
    return response