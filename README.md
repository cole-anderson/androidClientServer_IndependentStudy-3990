# 3990Project

Independent Study based on the study of clustering and how it can be applied to query processing.
Implmeneted a client app(android) and server where the server only transmits data to the client is the client "location" is outside a previously generated and sent minumum bound rectangle(MBR) based off of a number of clusters of points. The client incrementally moved along a preset path(trajectory) where it finds the closest point (x,y) in the cluster to its given location (x,y)

Phase 1: (Complete)
-Client: Android application(simulated in android studio) that is able to
  a) parse an fnl file into a data structure
  b) ping the server if outside of a given cluster mbr
  c) find the closest point in the cluster to its current location
  d) display the current location and the closest location to the user
-Server: Java based socket server that is able to
  a) parse an arff file into a data structure
  b) determine number of clusters from the arff file header information
  c) send a given data structure to the client upon request
 
Phase 2: (ongoing):
-Assigning contextual data to each "cluster point" where in the client doesnt just see the closest point but extra information in regards to that point
-Actual map integration?
-TBD


-
