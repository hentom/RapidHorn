func main()
{
  Int[] a;
  const Int alength;
  const Int v;
  assume(0 <= alength);
  assume(alength < 3); // higher values make Z3/Spacer not terminate...

  Int i = 0;
  while(i < alength)
  {
    a[i] = (2 * i) + v;
    i = i + 1;
  }
  
  Int j = 0;
  while(j < alength)
  {
    assert(a[j] == (2 * j) + v);
    if (0 <= v)
    {
      assert((2 * j) <= a[j]);
    }
    if (0 < v)
    {
      assert((2 * j) < a[j]);
    }
    if (v == 80)
    {
      assert((2 * j) < a[j]);
    }
    j = j + 1;
  }
}
