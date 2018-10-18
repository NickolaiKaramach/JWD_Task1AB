package edu.etc.by.kickstart.observer;

public interface Supervised {
    //TODO: Should we add List there in interface?
    //List<Observer> listeners;

    void subscribe(Watcher watcher);

    void unsubscribe(Watcher watcher);

    void notifySubscribers();
}
