LB-API
======

The Lucky Bit API Java wrapper

This library provides some wrapper classes for the Lucky Bit API so that Java applications
can easily access the Lucky Bit database. This would be useful for a bet history analysis
tool, a betting bot, or a bitcoin address scraper (although that would be against the terms
of the license since it's evil).

To use, just call LuckyBit.nameOfAPICall(parameter) for the desired data. An appropriate
object containing the result (or null) will be returned. For data concerning the daily server
seeds and hashes, expect a Daily object that contains the date() of the seed and the data()
which can be seed or hash as requested. Information about the games are contained in the Game
class, and individual bets are represented by the Bet class.

No need to instance any objects. The API wrapper is seamless as long as you don't trouble
yourself with the object factory and connector (the kit package).
