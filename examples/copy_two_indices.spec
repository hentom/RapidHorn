func main()
{
  Int[] a;
  const Int[] b;
  const Int blength;
  assume(0 <= blength);
  assume(blength < 10);

  Int i = 0;
  Int j = 0;
  while(i < blength)
  {
    a[i] = b[j];
    i = i + 1;
    j = j + 1;
  }

  // check property
  Int k = 0;
  while(k < blength)
  {
    assert(a[k] == b[k]);
    k = k + 1;
  }
}
