public class AsciiCharSequence implements CharSequence {

    private byte[] byteArray;

    AsciiCharSequence(byte[] byteArray){
        this.byteArray = byteArray;
    }


    @Override
    public int length() {
        return this.byteArray.length;
    }

    @Override
    public char charAt(int index) {
        return (char) this.byteArray[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        int l = end - start;
        byte[] subSequence = new byte[l];
        for(int i=0; i<l; i++){
            subSequence[i] = this.byteArray[start+i];
        }

        return new AsciiCharSequence(subSequence);
    }

    @Override
    public String toString() {
  /*      int l = this.length();
      char[] result = new char[l];
      for(int i=0; i<l; i++){
          result[i] = this.charAt(i);
      }
       return new String(result); it wasn't necessary to create a string from chars, bytes are ok too*/
       return new String(this.byteArray);
    }
    // implementation
}
/* the reference solution is very good and elegant

class AsciiCharSequence implements CharSequence {
    private byte[] byteSequence;

    public AsciiCharSequence(byte[] bytesSequence) {
        this.byteSequence = bytesSequence;
    }

    @Override
    public int length() {
        return byteSequence.length;
    }

    @Override
    public char charAt(int index) {
        return (char) byteSequence[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(java.util.Arrays.copyOfRange(byteSequence, start, end));
    }

    @Override
    public String toString() {
        return new String(byteSequence);
    }
}
 */