module MksIce {
    interface Management {
        void initManagement( int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass );
        string getData();
    };
};
