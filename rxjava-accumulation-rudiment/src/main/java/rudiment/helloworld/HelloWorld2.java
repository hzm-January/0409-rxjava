package rudiment.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Package: rudiment.helloworld
 * Author: houzm
 * Date: Created in 2018/10/5 20:45
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： 使用 Observable.create(@NotNull io.reactivex.ObservableOnSubscribe<T> source) 函数创建Observable可被观察者
 */
public class HelloWorld2 {

    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        helloWorld();
//        2018-10-05 20:47:43.448 [main] DEBUG rudiment.helloworld.HelloWorld - onsubscribe
//        2018-10-05 20:47:43.451 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 1
//        2018-10-05 20:47:43.452 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 2
//        2018-10-05 20:47:43.452 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 3
//        2018-10-05 20:47:43.452 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 4
//        2018-10-05 20:47:43.452 [main] DEBUG rudiment.helloworld.HelloWorld - onNext : 5
    }

    private static void helloWorld() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("1");
                observableEmitter.onNext("2");
                observableEmitter.onNext("3");
                observableEmitter.onNext("4");
                observableEmitter.onNext("5");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                logger.debug("onsubscribe");
            }

            @Override
            public void onNext(String s) {
                logger.debug("onNext : " + s);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.debug("onError");
            }

            @Override
            public void onComplete() {
                logger.debug("onComplete");
            }
        });
    }
}
