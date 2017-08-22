# ct-tech-exercise
commercetools technical exercise

This repository contains the application I created for the commercetools technical exercise activity. Below are some notes about the application as well as instructions to run and test.

The application does not run on any server, such as Tomcat or WebSphere. All functionality can be ran from the designated impl class, which in this case is:

Synchronize in the package: ct.tech.exercise.sync

Synchronize has a main method that will simulate making api calls to get the necassary information for the exercise. 
The simulated calls are:

1. Call to api to get store id's (Etsy store id's)
2. Call to get listings for given store id's

The exercise, as I understood it, wanted to be able to synchronize the listings for the shop id's to an output file. To test this, I added two test cases in the following class:

ShopListingService in the package: ct.tech.exercise.service

This class has a method:

getShopListings

In this method I have left a comment block about how to simulate getting back different listing's for shop id's. The first run will return a set of listings, and the second one will simulate returning a different set of listings pulled at some later time.

For testing you can simply comment out one line or the other depending on what you would like to test. i.e.
1. Testing for initial output
2. Testing for no change in listings
3. Testing for removal of listings
4. Testing for addition of listings
5. Testing for both addition and removal NOTE: (for this test, you may have to modify one of the existing "shopId"_listings_updates.json files)

Finally, I have added two folders containing sample responses from the Etsy api:

getShop
findAllShopListingsActive

The getShop output is saved in a json file: openapi.etsy.com2.json NOTE: (this is the one used for testing)
The findAllShopListingsActive output is saved in the jsonListings folder and contains the listings for a given shop (the shop id's from the above json file "openapi.etsy.com2.json"

There are two other folders. One that has modifications to the jsonListings labeled: 

jsonListingsUpdates

And the final folder:

listings

which contains the output for the applications. Two files are saved per shop id, the first file is the current listing inventory and the second is the output file that lists the changes since the last run i.e. the output that the technical exercise was asking for. The file containing the current listings is created to perist the listings per shop id from one api call to the next so that comparison can be done. This was my solution for comparing the existing listings with the incoming listings since I am not using and database or other storage system and also not activelly calling api's that can return those pieces.

