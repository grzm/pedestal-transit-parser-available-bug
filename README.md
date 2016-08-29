# trapar

Test case to demostrate transit-params bug introduced by
[Handle empty transit body params](https://github.com/pedestal/pedestal/commit/d948b6122bbe086562419efa36446f9bd330115c)

The [`(zero? (.available body))` check][zero-check] is considering request bodies which aren't
empty.

[zero-check]: https://github.com/pedestal/pedestal/blob/d948b6122bbe086562419efa36446f9bd330115c/service/src/io/pedestal/http/body_params.clj#L126

The repo has two branches: `master` and `0.5.0`. The only difference between the
two branches is the version of Pedestal specified in the project file.

## To demonstrate

In the repo

    git checkout master
    lein test

You should see the following result:

    lein test trapar.service-test
    INFO  io.pedestal.http - {:msg "GET /transit", :line 78}

    lein test :only trapar.service-test/transit-test

    FAIL in (transit-test) (service_test.clj:16)
    expected: (= "{:b 2, :a 1}" body)
      actual: (not (= "{:b 2, :a 1}" "nil"))

    Ran 1 tests containing 1 assertions.
    1 failures, 0 errors.
    Tests failed.

And to confirm it worked in 0.5.0:

    git checkout 0.5.0
    lein test

You should see the following result:

    lein test trapar.service-test
    INFO  io.pedestal.http - {:msg "GET /transit", :line 78}

    Ran 1 tests containing 1 assertions.
    0 failures, 0 errors.

