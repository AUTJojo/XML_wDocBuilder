final static int anzahl_werte = 9;
	
	public static void main(String[] args) throws IOException {	
		String s = getFile("data.txt");
		s = s.replaceAll(" ", "");
		String [][] array = csvToArray(s);
		arrayToXML(array);
	}
	
	public static String getFile(String path) throws IOException{
		FileReader fr = null;
		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e1) {
			System.out.println("Bitte geben Sie ein gültiges Dokument ab.");
		}
	    BufferedReader br = new BufferedReader(fr);

	    String s = "";
		try {
			s = br.readLine();
		} catch (IOException e) {
			System.out.println("Bitte Überprüfen Sie ihr Textdokument!2");
		}

	    br.close();
		
		return s;
	}
	
	public static void array_ausagbe(String[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < anzahl_werte; j++){
				System.out.print(array[i][j]);
			}
		System.out.println();
		}
	}
	
	public static String[][] csvToArray(String s){
		String[] s_split = s.split(";");
		String[][] array = new String[s_split.length][anzahl_werte];
		for(int i = 0; i < s_split.length; i++){
			String x = s_split[i];
			String[] y = x.split(",");
			for(int j = 0; j < y.length; j++){
				array[i][j] = y[j];
			}
		}
		return array;
	}
	
	public static void arrayToXML(String [][] array){
		
	try{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
		Document doc = docBuilder.newDocument();
	
	  	Element rootElement = doc.createElement("Manschaft");
	  	doc.appendChild(rootElement);
		for(int i = 0; i < array.length; i++){
			Element player = doc.createElement("Spieler");
			rootElement.appendChild(player);

			Element firstname = doc.createElement("Vorname");
			firstname.appendChild(doc.createTextNode(array[i][0]));
			player.appendChild(firstname);

			Element lastname = doc.createElement("Nachname");
			lastname.appendChild(doc.createTextNode(array[i][1]));
			player.appendChild(lastname);
			  
			Element address = doc.createElement("Adresse");
			player.appendChild(address);
	
			Element street = doc.createElement("Straße");
			street.appendChild(doc.createTextNode(array[i][2]));
			address.appendChild(street);

			Element zip = doc.createElement("Zip");
			zip.appendChild(doc.createTextNode(array[i][3]));
			address.appendChild(zip);
			  
			Element place = doc.createElement("Place");
			place.appendChild(doc.createTextNode(array[i][4]));
			address.appendChild(place);
			  
			Element position = doc.createElement("Position");
			position.appendChild(doc.createTextNode(array[i][5]));
			player.appendChild(position);
			  
			Element number = doc.createElement("Nummer");
			number.appendChild(doc.createTextNode(array[i][6]));
			player.appendChild(number);
			  
			Element games_played = doc.createElement("GespielteSpiele");
			games_played.appendChild(doc.createTextNode(array[i][7]));
			player.appendChild(games_played);
			  
			Element goales = doc.createElement("Tore");
			goales.appendChild(doc.createTextNode(array[i][8]));
			player.appendChild(goales);
		}  
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
	
		StreamResult result =  new StreamResult(new File("test.xml"));
		transformer.transform(source, result);
	
		System.out.println("Done");
	
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(TransformerException tfe){
		  tfe.printStackTrace();
		}
	}
