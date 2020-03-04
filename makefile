#coles 2020 java makefile
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
				$(JC) $(JFLAGS) $*.java

CLASSES = \
				parser.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
				$(RM) *.class
