import { Picture } from './picture';
import { Post } from './post';

describe('Post', () => {
  it('should create an instance', () => {
    expect(new Post(0, '', new Picture(0, '', '', 0), [])).toBeTruthy();
  });
});
