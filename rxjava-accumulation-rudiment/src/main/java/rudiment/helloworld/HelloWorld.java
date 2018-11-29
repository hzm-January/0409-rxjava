package rudiment.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Package: PACKAGE_NAME
 * Author: houzm
 * Date: Created in 2018/10/5 20:33
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： RxJava-hello world
 */
public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {

        helloWorld();
//        2018-10-05 20:46:39.083 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 1
//        2018-10-05 20:46:39.086 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 2
//        2018-10-05 20:46:39.086 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 3
//        2018-10-05 20:46:39.086 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 4
//        2018-10-05 20:46:39.086 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 5
    }

    private static void helloWorld() {
        Observable<String> observable = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("1");
                observer.onNext("2");
                observer.onNext("3");
                observer.onNext("4");
                observer.onNext("5");
            }
        };

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                logger.debug("onsubscribe");
            }

            @Override
            public void onNext(String s) {
                logger.debug("onNext : "+s);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.debug("onError");
            }

            @Override
            public void onComplete() {
                logger.debug("onComplete");
            }
        };
        observable.subscribe(observer);
    }
}
