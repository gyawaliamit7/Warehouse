JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $*.java

CLASSES = \
		ProductList.java \
		ProductIdServer.java \
		Product.java \
		Price.java \
		ClientList.java \
		ClientIdServer.java \
		Client.java \
		ManufacturerList.java \
		ManufacturerIdServer.java \
		Manufacturer.java \
		OrderIdServer.java \
		Order.java \
		TransactionIdServer.java \
		WareHouse.java \
		UserInterface.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
