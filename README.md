# GuessMaster
First Android App

This is my first attempt at and Android app (past a blank activity. It's a guessing game that finds your secret number by guessing numbers and taking feedback from you. I had to make the algorithm for a CS class, and I thought it was really cool, so I decided to see if I could put it a format others could use. Since I'm a huge Android fan, I thought I'd use it as an opportunity to get into Android development.

Right now, it's not super user friendly -- I made use of Android's AlertDialogs to prompt user input, just because it was easier than making an interface in another Activity. When I get the chance, I'll move the gameplay to another Activity, which will look much nicer.

Overall, the algorithm is pretty good -- it can find your number in less than 13 guesses, but that's worst case. On average, it guesses in about 8-10 guesses. One problem is that if you forget your number and are no longer able to give the algorithm match data, the algorithm will eventually fail. But if you forget your card, is it the magician's fault you can't identify it when they find it?
