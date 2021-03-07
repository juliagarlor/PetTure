import { Picture } from './picture';

describe('Picture', () => {
  it('should create an instance', () => {
    expect(new Picture(0, '', '', 0)).toBeTruthy();
  });
});
