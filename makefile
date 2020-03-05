#coles 2020 java makefile
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
				$(JC) $(JFLAGS) $*.java

CLASSES = \
				parser.java \
				object.java

MAIN = parser

default: classes

classes: $(CLASSES:.java=.class)

clean:
				$(RM) *.class
