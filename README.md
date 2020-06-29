# ExternalHWInfoGUI
Small GUI to display measurements from HWInfo externally.


Customization:

HWInfoConnection.java:
str_url needs to be set to your IP.
You may need to code the whole new for loop inside the getHWInfoJSON() method.
Or simply replace the correct SensorClass Strings and set the amount of cases to fit with your core count etc.

Create your own GUI:

I modified some classes of the https://github.com/torakiki/fx-progress-circle Repo to fit my needs.
So i am now able to change min, max and the unit of the progressbar. Example: CPU Clock ranges from 0 MHz up to 4200MHz (my CPU). So i had to modify it.

As i couldn't change the font size to be readable inside the progresscircle, i had to create a different version of those classes (RingProgressIndicator_Clock.java) to lower the font size.

I added some more features, like turning off and on the touchscreen of my Raspberry Pi display, which i use to display the fetched data.

Feel free to ask me any questions via issues.
