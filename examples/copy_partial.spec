func main(Int k)
{
  Int[] a;
  const Int[] b;
  const Int blength;
  assume(0 <= blength);
  assume(blength < 3);
  assume(k <= blength);
  
  Int alength = 0;
  while(alength < k)
  {
    a[alength] = b[alength];
    alength = alength + 1;
  }
  
  Int j = 0;
  while(j < k)
  {
    assert(a[j] == b[j]);
    j = j + 1;
  }
}
