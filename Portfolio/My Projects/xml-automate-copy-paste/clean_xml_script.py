import glob
import os

if not "../result":
    os.mkdir("result")
result = "result/xml_concat.xml"
app1 = 'ENCABEZADO_APP1\nENCABEZADO_APP1\nENCABEZADO_APP1\nENCABEZADO_APP1\n\n'
app2 = 'ENCABEZADO_APP2\nENCABEZADO_APP2\n\n'
app1_cierre = "CIERRE_APP1"
app2_cierre = "CIERRE_APP2"
option = input("Introduzca número: 1 para app1 y 2 para app2.\n")
option
with open(result,"w") as clean:
    clean.write("")

if option == "1":
    with open(result,"a") as clean:
        clean.write(app1)
        for file in glob.glob("*.xml"): #file es todos los archivos chicos
            with open(file,"r") as xml_file:
                lines = xml_file.readlines()
                append = False
                for line in lines:
                    if line.startswith("<b"):
                        append = True
                        clean.write(line)
                    elif line.startswith("</b"):
                        append = False
                        clean.write(line)
                    elif append == True:
                        clean.write(line)
        clean.write("\n")
        clean.write(app1_cierre)
elif option == "2":
    with open(result,"a") as clean:
        clean.write(app2)
        for file in glob.glob("*.xml"): #file es todos los archivos chicos
            with open(file,"r") as xml_file:
                lines = xml_file.readlines()
                append = False
                for line in lines:
                    if line.startswith("<b"):
                        append = True
                        clean.write(line)
                    elif line.startswith("</b"):
                        append = False
                        clean.write(line)
                    elif append == True:
                        clean.write(line)
        clean.write("\n")
        clean.write(app2_cierre)