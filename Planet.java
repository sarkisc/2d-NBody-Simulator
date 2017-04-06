// Planet class

class Planet{

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private double G = 6.67 * Math.pow(10,-11);

    // Planet constructor
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // Planet copy constructor
    public Planet(Planet p)
    {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    // calculate distance between two planets
    // samh.calcDistance(rocinante);
    public double calcDistance(Planet p)
    {
        return Math.sqrt( Math.pow(this.xxPos-p.xxPos,2)+Math.pow(this.yyPos-p.yyPos,2) );
    }

    // takes in a planet, and returns a double describing
    // (the magnitude of) the force exerted on this planet by the given planet
    // samh.calcForceExertedBy(rocinante)

    public double calcForceExertedBy(Planet p)
    {
        return (G * this.mass * p.mass)/Math.pow(calcDistance(p),2);
    }

    public double calcForceExertedByX(Planet p)
    {
        return (p.xxPos-this.xxPos)/(calcDistance(p)) * calcForceExertedBy(p);
    }


    public double calcForceExertedByY(Planet p)
    {
        return (p.yyPos-this.yyPos)/(calcDistance(p)) * calcForceExertedBy(p);
    }

    // Planet[] allPlanets = {samh, rocinante, aegir};
    // samh.calcNetForceExertedByX(allPlanets);
    // samh.calcNetForceExertedByY(allPlanets);
    public double calcNetForceExertedByX(Planet[] planets)
    {
        int numPlanets = planets.length;
        double netForceX = 0;
        for(int i=0;i<numPlanets;i++)
        {
            if ( !(this.equals(planets[i])) )
                netForceX += calcForceExertedByX(planets[i]);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets)
    {
        int numPlanets = planets.length;
        double netForceY = 0;
        for(int i=0;i<numPlanets;i++)
        {
            if ( !(this.equals(planets[i])) )
                netForceY += calcForceExertedByY(planets[i]);
        }
        return netForceY;
    }

    public void update(double dt, double Fx, double Fy)
    {
        double accX = Fx / this.mass;
        double accY = Fy / this.mass;

        this.xxVel += accX*dt;
        this.yyVel += accY*dt;

        this.xxPos += this.xxVel*dt;
        this.yyPos += this.yyVel*dt;
    }

    public void draw()
    {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }


}