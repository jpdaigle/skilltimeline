SkillTimeline: Sprucing up technical resumes!
=============================================

SkillTimeline generates an SVG file representing a timeline of your professional 
experience with different technology stacks, suitable for inclusion in a resume.

**Screenshot**

<img src="https://github.com/jpdaigle/skilltimeline/raw/master/sample-screenshot.png" alt="Screenshot"/>


BUILD / INSTALL
===============
Invoke:

    $ ant clean build
    
A fresh jar file (``SkillTimeline.jar``) should now be built.

USAGE
============

    $ java -jar SkillTimeline.jar INPUTFILENAME

The input file is formatted as follows:

    #Sample SkillTimeline input file
    Some skill: 2008.04-2009.09, 2010.01-2011.03 
    Some other skill: 2006-2010.06

Each line can contain multiple comma-separated time ranges. A time range is expressed
as two dates separated by a dash (``-``). You can have as much detail as you want: each date can be
expressed, with increasing precision, as:

* ``yyyy``
* ``yyyy.MM``
* ``yyyy.MM.dd``

The output file will be written to the same directory as the input, with filename ``INPUTFILENAME.svg``.

To get the ``svg`` file into your resume, you'll need to either convert it to 
PDF and include it in a LaTeX resume, or rasterize it at the appropriate resolution. 
In any case, [Inkscape](http://inkscape.org/) can prove very useful here.

LICENSE
========
[GNU LGPL 3.0](http://www.gnu.org/licenses/lgpl-3.0.txt)

(c)2011 Jean-Philippe Daigle (jpdaigle@gmail.com)

