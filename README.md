the-escape
==========
Author: Irene X Chen
Start Date: November 3, 2012
==========
Text adventure coded up in Java for the Codeacademy intern hiring process.

There is an up-to-date .jar executable in the target folder - please run the one
called 'target/the-escape-1.0-jar-with-dependencies.jar'.  On the other hand, you
may also build a new .jar from the root directory with the command
'mvn assembly:assembly' (requires maven).

Tests and specifications have yet to be written; because of the time constraint,
I felt it was most important to achieve the status of a working prototype and
devoted the majority of my time to completing the implementation.  However, now
that a working prototype status has been achieved, I would like to go back to
properly test and document code as well as refactor like crazy.

I originally tried to design my code such that all of the information specific
to my particular game would be separated into the Model and Scanner classes, so
that a different game could easily be loaded into the system, but I found, as
time wore on, it was quite difficult to keep my code that clean.  I eventually
gave in and hardcoded in special item subclasses as well as special commands, but
in the future, I would like to revisit my design (currently, it's a bit of a mess
because of my decision to change my design in the middle).

Furthermore, within my Game class, I have a very long if statement, and with more
time, I would like to go back and convert it to a switch statement for clarity
with the help of a Command enum.  Also within my Game class, I implemented a
hack-ish fix to a dependency problem I ran into - when a player interacted with
the safe, I would've liked to call safe.interact(), but that particular method
required user input and thus a parser object.  I would have also liked to address
that issue properly instead of implementing my hacked-up solution.

Finally, I should have overriden equals properly for each of my data objects,
except for the purposes of this prototype, the standard Object.equals() method
sufficed, so I did not bother.