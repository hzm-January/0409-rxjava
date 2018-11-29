package rudiment.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Package: rudiment.error
 * Author: houzm
 * Date: Created in 2018/10/6 1:10
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： TODO
 */
public class ErrorTest {
    private static Logger logger = LoggerFactory.getLogger(ErrorTest.class);

    public static void main(String[] args) {
        helloWorld();
//        2018-10-06 01:11:48.611 [main] DEBUG rudiment.error.ErrorTest - onsubscribe
//        2018-10-06 01:11:48.613 [main] DEBUG rudiment.error.ErrorTest - Observable emmitter --> onNext-1
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - onNext : 1
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - Observable emmitter --> onNext-2
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - onNext : 2
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - Observable emmitter --> onNext-3
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - onNext : 3
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - onError
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - Observable emmitter --> onNext-4
//        2018-10-06 01:11:48.614 [main] DEBUG rudiment.error.ErrorTest - Observable emmitter --> onNext-5
    }

    private static void helloWorld() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                logger.debug("Observable emmitter --> onNext-1");
                observableEmitter.onNext("1");
                logger.debug("Observable emmitter --> onNext-2");
                observableEmitter.onNext("2");
                logger.debug("Observable emmitter --> onNext-3");
                observableEmitter.onNext("3");
                observableEmitter.onError(new IllegalStateException("非法状态异常"));
                logger.debug("Observable emmitter --> onNext-4");
                observableEmitter.onNext("4");
                logger.debug("Observable emmitter --> onNext-5");
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
