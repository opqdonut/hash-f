This Christmas, I want to gift you something practical. Something useful.
None of that
[artsy nonsense](https://gist.github.com/opqdonut/b3f755badfa37e1c6363b2419bfcffda)
from last year.

I recommend enjoying [the implementation](src/hash_f.clj) with a glass
of your favourite spirit.

Merry Christmas!

# `#f`

Have you ever wanted to nest `#()` anonymous functions in Clojure?

With `#f`, you can!

```
(require 'hash-f)
(map #f(map #f(* $1 2) $1) [[1 2] [3 4]])
  ==> ((2 4) (6 8))
```

You can even refer to arguments of enclosing `#f` functions!

```
(-> {:pages [{:page 1} {:page 2} {:page 3}]}
    (update :pages #f(mapv #f(update $1 :page #f(str $1 "/" (count $$$1)))
                           $1)))
  ==> {:pages [{:page "1/3"} {:page "2/3"} {:page "3/3"}]}
```

Just add `#f` to your deps.edn!

```
{:deps
 {io.github.opqdonut/hash-f {:git/sha "<commit hash here>"}}}
```
