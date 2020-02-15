func double(Int k)
{
    assume(k >= 0);
    Int r = 0;
    Int i = 0;
    while (i < k) {
        r = r + 2;
        i = i + 1;
    }
    assert(r == 2 * k);
}
