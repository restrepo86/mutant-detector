package co.com.mercado.libre.mutant.detector.api.configuration.router.enums;

public enum RouterEnum {

    MUTANT_DETECTOR("/mutant");

    private String route;

    RouterEnum(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

}
