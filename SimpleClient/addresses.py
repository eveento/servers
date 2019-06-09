def synchronous(argument):
    path = "{0}/http/uuid?id={1}".format(get_server_address(),argument)
    return path


def asynchronous(argument):
    path = "{0}/http/response?uuid={1}".format(get_server_address(),argument)
    return path


def get_server_address():
    return "http://localhost:8080"