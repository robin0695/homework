# Collections:

Write a java merge class that will merge two sorted collections of the same kind into a single sorted collection. You need to think of how to design this class in a generic form and efficiently.

code: src/main/java/com/robin/homework/q1

test: src/test/java/com/robin/homework/q1

# Concurrency:

Use java concurrency packages to write a queue that supports multi-writer and multi-reader, i.e. the writer pushes the stuff into the queue and the reader pops the stuff out the queue. All the writer's stuff can't be lost and must be into the queue, and each reader can't pops out the same stuff. Think of how you would simulate the situation and perform the testing. Using the java blocking queue is not allowed.

code: src/main/java/com/robin/homework/q2

test: src/test/java/com/robin/homework/q2

# OO Design:

Consider the following design problem and come up with a class hierarchy: Given a drawing mechanism, we always need to invoke some preprocessing before drawing, and postprocessing after drawing, and also each user will draw different shapes like circle, square, whatever he/she likes. Now, make an OO design of classes so that the routine preprocessing/postprocessing can be hidden, and the user can supply whatever the shape he/she wants to draw.

code: src/main/java/com/robin/homework/q3

test: src/test/java/com/robin/homework/q3

# DB Project:

Consider that we are a service provider to provide customers different services. Each customer can subscribe to multiple service and each service can be subscribed to by multiple customers. Come up a database schema to model the service-customer relationship. And use java/tomcat/db of your choice to implement a simple web service let the customer subscribe to a new service, modify or delete a subscribed service.

code: src/main/java/com/robin/homework/q4

test: src/test/java/com/robin/homework/q4