package rudiment.disposable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Package: rudiment.disposable
 * Author: houzm
 * Date: Created in 2018/10/5 22:44
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： Disposable 用法初探
 *
 * --------------------------------
 *  3->2->1
 * --------------------------------
 *          ---------------------------------
 *              3->2->1
 *          ---------------------------------
 */
public class DisposableDemo {
    private static Logger logger = LoggerFactory.getLogger(DisposableDemo.class);
    public static void main(String[] args) {
        disposeTest();
//        2018-10-05 22:54:13.519 [main] DEBUG rudiment.disposable.DisposableDemo - onsubscribe
//        2018-10-05 22:54:13.521 [main] DEBUG rudiment.disposable.DisposableDemo - Observable emmitter --> onNext-1
//        2018-10-05 22:54:13.522 [main] DEBUG rudiment.disposable.DisposableDemo - onNext : 1
//        2018-10-05 22:54:13.522 [main] DEBUG rudiment.disposable.DisposableDemo - Observable emmitter --> onNext-2
//        2018-10-05 22:54:13.522 [main] DEBUG rudiment.disposable.DisposableDemo - observer-->if 2-->dispose
//        2018-10-05 22:54:13.522 [main] DEBUG rudiment.disposable.DisposableDemo - onError

    }

    private static void disposeTest() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                logger.debug("Observable emmitter --> onNext-1");
                observableEmitter.onNext("1");
                logger.debug("Observable emmitter --> onNext-2");
                observableEmitter.onNext("2");
                logger.debug("Observable emmitter --> onNext-3");
                observableEmitter.onNext("3");
                logger.debug("Observable emmitter --> onNext-4");
                observableEmitter.onNext("4");
                logger.debug("Observable emmitter --> onNext-5");
                observableEmitter.onNext("5");
            }
        }).subscribe(new Observer<String>() {
            private Disposable disposable;
            @Override
            public void onSubscribe(Disposable disposable) {
                logger.debug("onsubscribe");
            }

            @Override
            public void onNext(String s) {
                if ("2".equalsIgnoreCase(s)) {
                    logger.debug("observer-->if 2-->dispose");
                    disposable.dispose();
                    logger.debug("is disposed? -->"+disposable.isDisposed());
                }
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
