package lapr.project.model.esinf;

import lapr.project.model.*;
import lapr.project.model.store.*;
import lapr.project.ui.Utils;
import lapr.project.utils.Graph;

import java.util.*;

public class FreightNetwork {

    private int coresUtilizadas;

    private PortStore portStore;

    private CapitalStore capitalStore;

    public AdjacencyMatrixGraph<Place, Double> adjacencyMatrixGraph;


    public FreightNetwork(){
        adjacencyMatrixGraph = new AdjacencyMatrixGraph<>(true);
    }


    public AdjacencyMatrixGraph<Place,Double> getAdjacencyMatrixGraph(){
        return adjacencyMatrixGraph;
    }


    public void addNewInformation(CapitalStore capitalStore, PortStore portStore, SeadistStore seadistStore, BorderStore borderStore, int n){
        this.portStore=portStore;
        this.capitalStore=capitalStore;
        linkBetweenCapitalsOfNeighboringCountries(capitalStore,borderStore);
        connectionBetweenPortsOfTheSameCountry(portStore,seadistStore);
        connectionBetweenTheCapitalAndTheNearestPort(capitalStore,portStore);
        connectionBetweenThePortAndTheNearestNPortsOfAnotherCountry(seadistStore,n,portStore);
    }

    protected void linkBetweenCapitalsOfNeighboringCountries(CapitalStore capitalStore,BorderStore borderStore){

        for (Capital capital: capitalStore.getCapitalLst()){

             for (Border border: borderStore.getBorderLst()){
                if (border.getCountryName().equals(capital.getCountryName())){
                    Capital capital2 = capitalStore.getCapitalByCountryName(border.getCountryName2());
                    Double distance = Utils.calculateDistance(capital.getLatitude(),capital.getLongitude(),capital2.getLatitude(),capital2.getLongitude());
                    adjacencyMatrixGraph.addEdge(capital,capital2,distance);
                    adjacencyMatrixGraph.addEdge(capital2,capital,distance);

                }
            }

        }

    }

    protected void connectionBetweenPortsOfTheSameCountry(PortStore portStore,SeadistStore seadistStore){
        for (Seadist seadist: seadistStore.getSeadistLst()){
            if (seadist.getCountryName1().equals(seadist.getCountryName2())){
                double distance = seadist.getSeaDistance();
                Ports ports1 = portStore.getPortByName(seadist.getPortName1());
                Ports ports2 = portStore.getPortByName(seadist.getPortName2());
                adjacencyMatrixGraph.addEdge(ports1,ports2,distance);
                adjacencyMatrixGraph.addEdge(ports2,ports1,distance);
            }
        }
    }

    protected void connectionBetweenTheCapitalAndTheNearestPort(CapitalStore capitalStore,PortStore portStore){
        for (Capital capital: capitalStore.getCapitalLst()){
            double shortestDistance = Double.POSITIVE_INFINITY;
            int count=0;
            Ports aux=null;
            for (Ports ports: portStore.getPortsLst()){
                if (capital.getCountryName().equals(ports.getCountryName())){
                    double distance = Utils.calculateDistance(capital.getLatitude(),capital.getLongitude(),ports.getLatitude(),ports.getLongitude());
                    if ( distance<shortestDistance){
                        shortestDistance=distance;
                        aux=ports;
                        count++;
                    }
                }
            }
            if (count != 0){
                adjacencyMatrixGraph.addEdge(capital,aux,shortestDistance);
                adjacencyMatrixGraph.addEdge(aux,capital,shortestDistance);
            }
        }
    }

    protected void connectionBetweenThePortAndTheNearestNPortsOfAnotherCountry(SeadistStore seadistStore, int n,PortStore portStore){
        for (Ports ports: portStore.getPortsLst()){
            int count=0;
            TreeMap<Double, Ports> distanceList = new TreeMap<>();

            for (Seadist seadist: seadistStore.getSeadistLst()){
                if(!seadist.getCountryName1().equals(seadist.getCountryName2())){
                    if(ports.getPortName().equals(seadist.getPortName1()) || ports.getPortName().equals(seadist.getPortName2())){
                        double distance = seadist.getSeaDistance();
                        Ports ports1 = portStore.getPortByName(seadist.getPortName1());
                        Ports ports2 = portStore.getPortByName(seadist.getPortName2());
                        if (ports.getPortName().equals(seadist.getPortName1())){
                            distanceList.put(distance,ports2);
                            count++;
                        } else {
                            distanceList.put(distance,ports1);
                            count++;
                        }
                    }
                }
            }
            int temp;
            if (count < n) {
                temp = count;
            } else {
                temp = n;
            }

            for(int i=0; i<temp;i++){
                if(i<( distanceList.keySet()).size()){
                    double dist = new Vector<>(distanceList.keySet()).get(i);
                    Ports aux = new Vector<>(distanceList.values()).get(i);
                    adjacencyMatrixGraph.addEdge(ports,aux,dist);
                }
            }


        }
    }


    public Map<Capital, Integer> colorNetwork(){
        //mapa onde guarda os locais e respetiva cor
        Map<Capital, Integer> resultado = new TreeMap();

        //criar e preencher a lista de capitais
        Map<Capital,Integer> capitalsUnordered = new LinkedHashMap<>();

        for (Place place: adjacencyMatrixGraph.vertices()){
            if (place instanceof Capital){
                int degree=0;
                for (Place place1 : adjacencyMatrixGraph.adjVertices(place)){
                    if (place1 instanceof Capital){
                        degree++;
                    }
                }
                capitalsUnordered.put((Capital) place,degree);
            }
        }

        //ordenar Lista de locais pelo numero do grau
        List<Map.Entry<Capital,Integer>> capitalsOrdered = new ArrayList<>(capitalsUnordered.entrySet());
        capitalsOrdered.sort(Map.Entry.<Capital,Integer> comparingByValue().reversed());

        // colorir o grafo
        int corAtual = 0;

        while (!isFullyColored(resultado, capitalsOrdered)) {
            for (Map.Entry<Capital, Integer> map : capitalsOrdered) {
                if (!resultado.containsKey(map.getKey())) {
                    boolean flag = true;
                    for (Place place : adjacencyMatrixGraph.adjVertices(map.getKey())) {
                        if (place instanceof Capital && resultado.containsKey(place) && resultado.get(place) == corAtual) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        resultado.put(map.getKey(), corAtual);
                    }
                }
            }
            corAtual++;
        }
        coresUtilizadas=corAtual;
        return resultado;
    }

    private boolean isFullyColored(Map<Capital, Integer> resultado,List<Map.Entry<Capital,Integer>> capitalsOrdered ) {
        for (Map.Entry<Capital, Integer> map : capitalsOrdered) {
            if (!resultado.containsKey(map.getKey())) {
                return false;
            }
        }
        return true;
    }

    public int getCoresUtilizadas(){
        return coresUtilizadas;
    }

    public Map<String,List<Place>> mostCenteredCities(int n, CountryStore countryStore){
        // obter os continentes existentes
        List<String> continents = new ArrayList<>();
        for (Country country: countryStore.getCountryLst()){
            if(!continents.contains(country.getContinent())){
                continents.add(country.getContinent());

            }
        }

        Map<String,List<Place>> places = new TreeMap();
        for (String continent : continents){
            places.put(continent,mostCenteredCitiesOnTheContinent(n,continent));

        }

        return places;
    }

    public List<Place> mostCenteredCitiesOnTheContinent(int n, String continentName){

        Graph<Place, Double> graph = adjacencyMatrixGraph.clone();

        List<Place> centeredPlaces = new ArrayList<>();

        // obter só os locais de determinado continente
        for (Place p : adjacencyMatrixGraph.vertices()) {
            if (!p.getContinent().equals(continentName)) {
                graph.removeVertex(p);
            } else {
                centeredPlaces.add(p);
            }
        }

        AdjacencyMatrixGraph<Place, Double> matrixGraph = GraphAlgorithms.minDistGraph(graph,Double::compare, Double::sum);

        // ordenar os locais de ordem crescente de centralidade
        centeredPlaces.sort(new Comparator<Place>() {
            @Override
            public int compare(Place c1, Place c2) {
                double a = mediaDist(c1,matrixGraph);
                double b = mediaDist(c2,matrixGraph);

                return Double.compare(a, b);
            }
        });


        // retornar os n locais de centralidade de proximidade do continente
        if (centeredPlaces.size() < n) {
            n = centeredPlaces.size();
        }
        return centeredPlaces.subList(0, n);

    }


    public double mediaDist(Place p, AdjacencyMatrixGraph<Place, Double> matrixGraph) {
        double contador = 0;
        int aux=0;
        int key = matrixGraph.key(p);
        for(int i=0; i<matrixGraph.numVertices(); i++){
            if(i!=key){
                if(matrixGraph.edge(p,matrixGraph.vertex(i)) != null) {
                    contador += matrixGraph.edge(p, matrixGraph.vertex(i)).getWeight();
                    aux++;

                } else if (matrixGraph.edge(matrixGraph.vertex(i),p) != null && aux == 0){
                    contador += matrixGraph.edge(matrixGraph.vertex(i),p).getWeight();

                }
            }
        }
        return contador / (matrixGraph.numVertices() - 1);
    }


    public  List<String> getsPortsMoreCritical(int n) {
        List<String> resultado = new ArrayList<>();

            Integer[] result = new Integer[adjacencyMatrixGraph.numVertices()];
            String[] places = new String[adjacencyMatrixGraph.numVertices()];
            Double[] distances = new Double[adjacencyMatrixGraph.numVertices()];
            for (int i = 0; i < adjacencyMatrixGraph.numVertices(); i++) {
                result[i] = 0;
                distances[i] = 0.0;
                if (adjacencyMatrixGraph.vertex(i) instanceof Ports) {
                    places[i] = (((Ports) adjacencyMatrixGraph.vertex(i)).getPortName());
                } else if (adjacencyMatrixGraph.vertex(i) instanceof Capital) {
                    places[i] = (((Capital) adjacencyMatrixGraph.vertex(i)).getName());
                }
            }

            //obter quantos caminhos curtos passam por cada vértice
            for (int i = 0; i < adjacencyMatrixGraph.numVertices(); i++) {
                Place origem = adjacencyMatrixGraph.vertex(i);
                ArrayList<LinkedList<Place>> paths = new ArrayList<>();
                ArrayList<Double> dists = new ArrayList<>();
                GraphAlgorithms.shortestPaths(adjacencyMatrixGraph, origem, Double::compare, Double::sum, 0.0, paths, dists);
                double total = 0.0;
                for (int j = 0; j < adjacencyMatrixGraph.numVertices(); j++) {
                    if (dists.get(j) != null && dists.get(j) != 0.0) {
                        total += dists.get(j);
                        for (Place place : paths.get(j)) {
                            if (place instanceof Ports) {
                                int key = adjacencyMatrixGraph.key(place);
                                result[key] += 1;
                            }
                        }
                    }
                }
                distances[i] = total;
            }

            //ordenar
            int size = result.length;
            int temp;
            String aux;
            double tmp;
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < (size - i); j++) {
                    if (result[j - 1] > result[j] || (result[j - 1].equals(result[j]) && distances[j - 1] < distances[j])) {
                        //trocar elementos
                        temp = result[j - 1];
                        result[j - 1] = result[j];
                        result[j] = temp;
                        aux = places[j - 1];
                        places[j - 1] = places[j];
                        places[j] = aux;
                        tmp = distances[j - 1];
                        distances[j - 1] = distances[j];
                        distances[j] = tmp;
                    }

                }
            }

            // retornar os n locais de centralidade de proximidade do continente
            if (places.length < n) {
                n = places.length;
            }

            for (int i = places.length - 1; i > places.length - 1 - n; i--) {
                resultado.add(places[i]);
            }


        return resultado;

    }


    public  List<List<Place>> getShortestPaths(String origem, String destino, List<String> obligatoryPassage) {
        List<List<Place>> paths = new ArrayList<>();
        Place sourcePlace;
        if(portStore.getPortByName(origem) != null ){
            sourcePlace = portStore.getPortByName(origem);
        } else if (capitalStore.getCapitalByName(origem) != null){
            sourcePlace = capitalStore.getCapitalByName(origem);
        } else {
            return null;
        }

        Place destination;
        if(portStore.getPortByName(destino) != null ){
            destination = portStore.getPortByName(destino);
        } else if (capitalStore.getCapitalByName(destino) != null){
            destination = capitalStore.getCapitalByName(destino);
        } else {
            return null;
        }

        paths.add(getLandPath(sourcePlace,destination));
        paths.add(getMaritimePath(sourcePlace,destination));
        LinkedList<Place> shortPath = new LinkedList<>();
        GraphAlgorithms.shortestPath(adjacencyMatrixGraph,sourcePlace,destination,Double::compare,Double::sum,0.0,shortPath);
        paths.add(shortPath);

        List<Place> placesToVisite = new ArrayList<>();
        for (String place : obligatoryPassage){
            Place aux;
            if(portStore.getPortByName(place) != null ){
                aux = portStore.getPortByName(place);
                placesToVisite.add(aux);
            } else if (capitalStore.getCapitalByName(place) != null){
                aux = capitalStore.getCapitalByName(place);
                placesToVisite.add(aux);
            } else {
                return null;
            }
        }

        paths.add(getShortPathPassingThroughNIndicatedPlaces(placesToVisite,sourcePlace,destination));

        return paths;

    }

    public LinkedList<Place> getLandPath(Place origem, Place destino){
        Graph<Place, Double> graph = adjacencyMatrixGraph.clone();

        LinkedList<Place> shortPath = new LinkedList<>();

        for (Place p : adjacencyMatrixGraph.vertices()) {
            if (p instanceof Ports && (!p.equals(origem) || !p.equals(destino))) {
                graph.removeVertex(p);
            }
        }

        GraphAlgorithms.shortestPath(graph,origem,destino,Double::compare,Double::sum,0.0,shortPath);

        return shortPath;
    }

    public LinkedList<Place> getMaritimePath(Place origem, Place destino){
        Graph<Place, Double> graph = adjacencyMatrixGraph.clone();

        LinkedList<Place> shortPath = new LinkedList<>();

        if(origem instanceof Capital || destino instanceof Capital){
            return null;
        }

        // obter só os locais de determinado continente
        for (Place p : adjacencyMatrixGraph.vertices()) {
            if (p instanceof Capital) {
                graph.removeVertex(p);
            }
        }

        GraphAlgorithms.shortestPath(graph,origem,destino,Double::compare,Double::sum,0.0,shortPath);

        return shortPath;
    }

    private List<Place> getShortPathPassingThroughNIndicatedPlaces(List<Place> obligatoryPassage, Place p1, Place p2) {
        List<Place> intermediaryCitiesOrdered = new ArrayList<>();
        List<Place> checkedCities = new ArrayList<>();

        int n = obligatoryPassage.size();
        intermediaryCitiesOrdered.add(p1);

        for (int i = 0; i < n; i++) {
            List<Place> citiesChecked = searchNextCities(obligatoryPassage, p1, p2, checkedCities);
            if (citiesChecked == null) {
                continue;
            } else {
                citiesChecked.remove(citiesChecked.get(0));
                for (Place s : citiesChecked) {
                    intermediaryCitiesOrdered.add(s);
                    checkedCities.add(s);
                    obligatoryPassage.remove(s);
                }

                p1 = citiesChecked.get(citiesChecked.size() - 1);

            }
        }
        obligatoryPassage.add(p2);
        List<Place> finalPath = searchNextCities(obligatoryPassage, p1, p2, checkedCities);
        finalPath.remove(0);
        intermediaryCitiesOrdered.addAll(finalPath);
        return intermediaryCitiesOrdered;
    }

    private List<Place> searchNextCities (List<Place> obligatoryPassage, Place p1, Place p2, List<Place> citesAlreadyInPath) {
        double minDist;
        ArrayList<LinkedList<Place>> shortPaths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();
        for (int i = 0; i < obligatoryPassage.size(); i++) {
            LinkedList<Place> path = new LinkedList<>();
            double dist = GraphAlgorithms.shortestPath(adjacencyMatrixGraph, p1, obligatoryPassage.get(i),Double::compare,Double::sum,0.0, path);
            shortPaths.add(path);
            dists.add(dist);
        }
        for (int i=0; i<shortPaths.size(); i++) {
            if (shortPaths.get(i).size() == 1) {
                shortPaths.remove(shortPaths.get(i));
                dists.remove(dists.get(i));
            }
        }
        List<Place> aPath;
        if (dists.size() == 0) {
            aPath = null;
        } else {
            minDist = Collections.min(dists);
            int i = dists.indexOf(minDist);
            aPath = shortPaths.get(i);
        }
        return aPath;
    }




    public LinkedList<Place> mostEfficientCircuit (String locationName){
        //obter o local de início e fim
        Place sourcePlace;
        if(portStore.getPortByName(locationName) != null ){
            sourcePlace = portStore.getPortByName(locationName);
        } else if (capitalStore.getCapitalByName(locationName) != null){
            sourcePlace = capitalStore.getCapitalByName(locationName);
        } else {
            return null;
        }

        LinkedList<Place> result = new LinkedList<>();
        LinkedList<Place> visitedLocations = new LinkedList<>();

        Place adjacentLocation = null;
        Place localAtual = sourcePlace;

        result.add(sourcePlace);
        visitedLocations.add(sourcePlace);

        boolean temp = true;
        while (temp){
            double min = Double.MAX_VALUE;
            //obter caminho de menor distância
            for (Place location : adjacencyMatrixGraph.adjVertices(localAtual)){
                if((adjacencyMatrixGraph.edge(localAtual,location).getWeight() < min && !visitedLocations.contains(location) )|| (location.equals(sourcePlace) && visitedLocations.size()>2 && result.size()>1)){
                    min=adjacencyMatrixGraph.edge(localAtual,location).getWeight();
                    adjacentLocation=location;
                }
            }

            if (min != Double.MAX_VALUE){
                result.add(adjacentLocation);
                visitedLocations.add(adjacentLocation);
                localAtual = adjacentLocation;
            } else {
                if (visitedLocations.indexOf(localAtual) == 0){
                    return null;
                }
                localAtual=visitedLocations.get(visitedLocations.indexOf(localAtual)-1);
                if (result.size()>1){
                    result.remove(result.size()-1);
                }
            }

            if (localAtual.equals(sourcePlace)){
                temp = false;
            }
        }


        return result;
    }





}
