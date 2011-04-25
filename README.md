SkillTimeline
==============
## Sprucing up technical resumes!

SkillTimeline generates an SVG file representing a timeline of your professional 
experience with different technology stacks, suitable for inclusion in a resume.

BUILD / INSTALL
===============
    $ ant clean build
    

USAGE
============

    $ java -jar SkillTimeline.jar INPUTFILENAME

The input file is formatted as follows:

    #Sample SkillTimeline input file
    Some skill: 2008.04-2009.09, 2010.01-2011.03 
    Some other skill: 2006-2010.06

