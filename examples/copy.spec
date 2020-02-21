func main()
{
  const Int[] b;
  const Int blength;
  assume(0 <= blength);
  assume(blength < 10);

  Int[] a;

  Int i = 0;
  while(i < blength)
  {
    a[i] = b[i];
    i = i + 1;
  }

  Int j = 0;
  while(j < blength)
  {
    assert(a[j] == b[j]);
    j = j + 1;
  }
}
