import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveWDetailComponent } from './save-w-detail.component';

describe('SaveWDetailComponent', () => {
  let component: SaveWDetailComponent;
  let fixture: ComponentFixture<SaveWDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveWDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveWDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
