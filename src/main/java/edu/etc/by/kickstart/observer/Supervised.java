package edu.etc.by.kickstart.observer;

public interface Supervised {

    void subscribe(Watcher watcher);

    void unsubscribe(Watcher watcher);

    void notifySubscribers();
}
