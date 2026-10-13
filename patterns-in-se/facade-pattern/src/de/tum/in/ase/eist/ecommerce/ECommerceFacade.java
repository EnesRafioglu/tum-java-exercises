package de.tum.in.ase.eist.ecommerce;

public class ECommerceFacade {
    private final OrderController orderController;

    private final AdvertisementController advertisementController;

    private final ShippingController shippingController;

    public ECommerceFacade(){
        orderController = new OrderController();
        advertisementController = new AdvertisementController();
        shippingController = new ShippingController();
    }
    public void processOrder(Order order){
        orderController.processOrder(order);
    }
    public void processOrder(Order order, String s){
        orderController.processOrder(order,s);
    }
    public Order retrieveLatestOrder(int id){
        return orderController.retrieveLatestOrder(id);
    }
    public void playAdvertisement(int ageRestriction) {
        advertisementController.playAdvertisement(ageRestriction);
    }
    public void shipOrder(Order order,String adress){
        order.setShipping(shippingController.createShipping(adress));
        shippingController.shipOrder(order);
    }
}
