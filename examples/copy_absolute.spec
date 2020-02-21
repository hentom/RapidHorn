func main()
{
  Int[] a;
  const Int[] b;

  const Int blength;
  assume(0 <= blength);
  assume(blength < 10);

  Int i = 0;
  while(i < blength)
  {
    if(b[i] >= 0)
    {
      a[i] = b[i];
    }
    else
    {
      a[i] = - b[i];
    }
    i = i + 1;
  }
  
  Int j = 0;
  while(j < blength)
  {
    assert((a[j] == b[j]) || (a[j] == - b[j]));
    assert(0 <= a[j]);
    j = j + 1;
  }
}
