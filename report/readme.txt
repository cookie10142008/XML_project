explain how you compile & execute your files here...

Students:ªLÞ³ÂE¡BÁé©û­Û
Step 1: Execute ProvierApplet and load the XML file of the information about a specific provider in the bin file.

Step 2: On DistributorApplet, repeat Step 1.

Step 3: On ProviderApplet, go to the tabbed page "Distributor Connection" and press the button "Connect to distributor!", which assigns a port number randomly to this provider.

Step 4: On DistributorApplet, go to the tabbed page "Provider Connection" and press the button "Connect to provider!", and then input the port number assigned by the provider.

Step 5: On Distributor Application window, before starting to send puchase or supply list request, send the type of "AUTHENTICATE_DISTRIBUTOR" in the bottom of this application window first.

Step 6: On DistributorApplet, go to the tabbed page "Provider Need Items", select the items that the distributor wants to purchase, and press the button "Supply List Request". (press ctrl to choose multiple items)

Step 7: Once "Supply List Request" is pressed, the window "Purchase List" shows up. After inputting the amount for each items in the needed column, the distributor has to press the "OK" button.

Step 8: The supply list will be shown in XML format on the message box(distribution application window). Then, change the message type into "Request Supply List", and send the message.

Step 9: After receiving the message about the supply list from the distributor, the provider will check whether it has enough items to supply and show supply list reponse on the message box.

Step 10: When the provider has enough items, it can move on to send purchase request with the distributor now.

Step 11: Once distributors connect to providers, both of them can start to send broadcast messages or terminate message to close the connection.

p.s. Multiple distributors can connect to the same provider when they input the same port number, and vice versa.


