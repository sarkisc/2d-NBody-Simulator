// NBody class

public  class NBody {

    // Given a file name, return a double corresponding to the radius of
    // the universe in that file, e.g. readRadius("./data/planets.txt")
    public static double readRadius(String filename)
    {
        In in = new In(filename);
        int numPlanets = in.readInt();  // numPlanets is not used

        return in.readDouble();

    }
    //Given a file name, return an array of Planets corresponding
    // to the planets in the file, e.g. readPlanets("./data/planets.txt")
    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        int numPlanets = in.readInt();

        double univSize = in.readDouble();  // univSize is not used

        Planet[] planets = new Planet[numPlanets];
        double xP;
        double yP;
        double xV;
        double yV;
        double mass;
        String imgFN;

        for(int i = 0; i < numPlanets; i++)
        {
            xP = in.readDouble();
            yP = in.readDouble();
            xV = in.readDouble();
            yV = in.readDouble();
            mass = in.readDouble();
            imgFN = "./images/" + in.readString();

            planets[i] = new Planet(xP, yP, xV, yV, mass, imgFN);
        }

        return planets;
    }

    // type the following at command line to test:
    // java NBody 15778800.0 25000.0 data/planets.txt
    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2]; // should be "./data/planets.txt"

        double universe_radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int numPlanets = planets.length;

        StdDraw.setScale(-universe_radius/2, universe_radius/2);
        String imageToDraw = "./images/starfield.jpg";
        StdDraw.picture(0,0,imageToDraw);

        for(Planet planet: planets)
        {
            planet.draw();
        }

        StdAudio.play("./audio/2001.mid");

        double[] xForces = new double[numPlanets];
        double[] yForces = new double[numPlanets];

        double time = 0.0;
        while(time < T)
        {
            // Calculate the net x and y forces for each planet,
            // storing these in the xForces and yForces arrays respectively.
            for (int i = 0; i < numPlanets; i++)
            {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
            }

            for (int i = 0; i < numPlanets; i++)
            {
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // Call update on each of the planets. This will update
            // each planet's position, velocity, and acceleration.
            for(int i = 0; i < numPlanets; i++)
            {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // Draw the background image.
            StdDraw.picture(0,0,imageToDraw);

            // Draw all of the planets.
            for(Planet planet: planets)
            {
                planet.draw();
            }

            // Pause the animation for 10 milliseconds.
            StdDraw.show(10);

            // Increase the time variable by dt.
            time += dt;
        }

    }


}
