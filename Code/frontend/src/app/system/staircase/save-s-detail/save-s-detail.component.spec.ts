import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveSDetailComponent } from './save-s-detail.component';

describe('SaveSDetailComponent', () => {
  let component: SaveSDetailComponent;
  let fixture: ComponentFixture<SaveSDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveSDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveSDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
