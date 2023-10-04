# iot-demo-lambda 

A simple serverless back-end API demo for a hypothetical smart door product.

## Overview

The purpose of this project is to demonstrate the use of:

* Java 17
* AWS Lambda (Serverless Deployment)
* Amazon Dynamo DB

A lambda function accepts HTTP requests and logs those requests to an Amazon Dynamo DB database.

The goal is not a complete working system, but a demo of efficient IoT scalability in the cloud.   For example, client
authentication is not implemented.


## Setup

The code does not require AWS credentials given that it runs in AWS Lambda, however permissions have to be assigned to
the lambda function in the AWS console to access DynamoDB tables.

Dynamo DB tables must be created.  To do this, upload the jar to teh AWS Lambda UI and map the following function:

    TableSetup.handleRequest

Calling that function will create the necessary tables, this can be done in the *Test* tab and does not need to be
mapped to a public URL.

In the project root directory:

    mvn package

...will build a jar file under target/.  

Deploy the jar file to AWS Lambda in the Lambda Functions section of the AWS web UI console.  In the lambda setup,
map the following function:

    IotDemo.handleRequest

Enable a public URL with the lambda function.  Alternatively, AWS API Gateway may be used but this is not necessary.

Note that the payload format for a public URL is compatible with AWS API Gateway 2 format and thus the Test tab will not
work for this function as the Lambda Test tab does not wrap the HTTP body payload with this format.


## Running

To send events to the Lambda URL, the JSON event payload format is as follows:

    {
        "clientTimestamp": "2023-10-04T13:00:00.00Z",
        "type": "LIGHT",
        "state": "off",
        "details": "Any device-specific details here"
    }


The following curl command may alternatively be used:

    curl --location 'https://youramazonlambdaurl.lambda-url.us-east-2.on.aws/TESTCLIENTID' \
    --header 'Content-Type: application/json' \
    --data '{
    "clientTimestamp": "2023-10-04T13:00:00.00Z",
    "type": "LIGHT",
    "state": "off",
    "details": "Any device-specific details here"
    }'



## Next Steps

Some next steps could be implemented to take this project into a more complete IoT system.


* Try out [Spring Cloud Function](https://spring.io/projects/spring-cloud) for a cloud-agnostic back-end that can be delivered to AWS, Azure, GCP and 
   others.
* Implement REST API for managing clients and device state.
* Explore the use of *websockets* possibly using Amazon API Gateway and Lambda to implement highly 
   scalable push events.  This will allow devices to be controlled centrally, even when behind a (recent) HTTP proxy or 
   firewall.  Fallback mechanisms to TCP, UDP, long-running HTTP connections, etc. may be needed when websockets are not
   available.
* Security!  Implement a PKI setup to authenticate client private keys using digital signatures for the event payload.
* More rigid boundary checking to prevent several types of attacks.
* DDoS mitigation.
* Mobile-first web-based UI for controlling device state.  CORS should be enabled to allow redirection to the service.


