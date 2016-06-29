package com.app.njl.activity.scenerypicker.bean;

import java.util.List;

/**
 * Created by root on 2016/6/21.
 */
public class SceneryListMessage {
    private List<Destination> destinations;

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "SceneryListMessage{" +
                "destinations='" + destinations + '\'' +
                '}';
    }
}
