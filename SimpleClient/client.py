# libs
import requests

# my files
import addresses

def job(uuid):
    first_job_result = first_job(uuid)
    result = first_job_result.text

    percentage_done = "0"
    print(first_job_result)
    while percentage_done != "100.0":
        second_job_result = second_job(result)
        percentage_done = second_job_result.text

def first_job(argument):
    response = requests.get(addresses.synchronous(argument))
    return response


def second_job(argument):
    response = requests.get(addresses.asynchronous(argument))
    return response