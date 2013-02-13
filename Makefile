JAVAC=javac
JAVACFLAGS=

SRCS=src/com/optifyTest/*.java
EXEC=OptifyAutomationSuite.sh


all: compile

compile:
	$(JAVAC) $(JAVACFLAGS) $(SRCS)
	chmod +x $(EXEC)

tar:
	$(TAR) $(TARFLAGS) $(TARNAME) $(TARSRCS)

clean:
	rm -f *.class *~
